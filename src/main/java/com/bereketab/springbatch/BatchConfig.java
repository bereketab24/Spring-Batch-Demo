package com.bereketab.springbatch;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Customer> itemReader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("customerItemReader")
                .resource(new ClassPathResource("customers-100.csv"))
                .linesToSkip(1)
                .delimited()
                .names(
                        "id",
                        "customerId",
                        "firstName",
                        "lastName",
                        "company",
                        "city",
                        "country",
                        "priPhoneNumber",
                        "secPhoneNumber",
                        "email",
                        "subscriptionDate",
                        "website")
                .targetType(Customer.class)
                .build();
    }

    @Bean
    public ItemProcessor<Customer , Customer> processor(){
        return customer ->{
            customer.setLastName(customer.getLastName().toUpperCase());
            return customer;
        };
    }

    @Bean
    public JdbcBatchItemWriter<Customer> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Customer>()
                .sql("INSERT INTO customers (id,customerId,firstName,lastName,company,city,country,priPhoneNumber, secPhoneNumber, email, subscriptionDate, website) VALUES (:id, :customerId, :firstName, :lastName, :company, :city, :country, :priPhoneNumber, :secPhoneNumber, :email, :subscriptionDate, :website)")
                .dataSource(dataSource)
                .beanMapped()
                .build();

    }


    @Bean
    public Step step(JobRepository jobRepository,
                     DataSourceTransactionManager transactionManager,
                     FlatFileItemReader<Customer> itemReader,
                     ItemProcessor<Customer,Customer> processor,
                     JdbcBatchItemWriter<Customer> writer){
        return new StepBuilder("step",jobRepository)
                .<Customer, Customer>chunk(10)
                .transactionManager(transactionManager)
                .reader(itemReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importCustomerJob(JobRepository jobRepository, Step step){
        return new JobBuilder("importCustomerJob", jobRepository)
                .start(step)
                .build();
    }
}
