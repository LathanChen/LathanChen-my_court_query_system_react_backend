package com.example.demo.batch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.mapper.CourtOpenInfoMapper;

@Configuration
@EnableBatchProcessing
public class ItemCountByCourtConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final CourtOpenInfoMapper courtOpenInfoMapper;
//	DataSource 是在 Java 应用程序中管理数据库连接的接口。它提供了一种抽象方式来连接和管理数据库，使你能够轻松地获取、释放和管理数据库连接，以及执行数据库操作。
	private final DataSource dataSource;
//	SqlSessionFactory 是 MyBatis 框架中的一个重要接口，它用于创建和管理数据库会话（Session）的工厂。
//	数据库会话是 MyBatis 中进行数据库操作的核心对象，它提供了执行 SQL 查询、更新等操作的方法，同时也负责管理数据库连接的获取和释放。
	private final SqlSessionFactory sqlSessionFactory;
//	PlatformTransactionManager 是 Spring 框架中用于管理事务的核心接口。
//	事务是一组数据库操作，要么全部成功执行，要么全部失败回滚，以确保数据的一致性和完整性。
	private final PlatformTransactionManager transactionManager;

	public ItemCountByCourtConfig(
			JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory,
			CourtOpenInfoMapper courtOpenInfoMapper,
			DataSource dataSource,
			SqlSessionFactory sqlSessionFactory,
			PlatformTransactionManager transactionManager) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.courtOpenInfoMapper = courtOpenInfoMapper;
		this.dataSource = dataSource;
		this.sqlSessionFactory = sqlSessionFactory;
		this.transactionManager = transactionManager;
	}

	@Bean
	public Job itemCountByCourtJob() {
		return jobBuilderFactory
//				job的名字
				.get("itemCountByCourtJob")
				.listener(myJobListener())
				.start(step())
				.build();
	}

	@Bean
	public JobExecutionListener myJobListener() {
//		匿名内部类允许您在定义的同时实现一个接口或继承一个类，并且可以在匿名内部类中重写接口或父类中的方法。
		return new JobExecutionListenerSupport() {
			@Override
			public void beforeJob(JobExecution jobExecution) {
				Date startTime = jobExecution.getStartTime();
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            String formattedStartTime = sdf.format(startTime);
	            System.out.println("作业开始时间：" + formattedStartTime);
			}

			@Override
			public void afterJob(JobExecution jobExecution) {
				Date startTime = jobExecution.getStartTime();
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            String formattedStartTime = sdf.format(startTime);
	            System.out.println("作业结束时间：" + formattedStartTime);
			}
		};
	}

	@Bean
	public Step step() {
		return stepBuilderFactory.get("step")
//	        	从数据库中读取一组CourtOpenInfo类型的数据，并将其处理为CourtOpenInfo类型
				.<CourtOpenInfo, CourtOpenInfo>chunk(10)
				.reader(reader())
				.processor(processor())
	            .writer(writer())
				.build();
	}

	@Bean
	@StepScope
//	泛型中指定的是返回值的类型，也就是Mybatis的查询会返回CourtOpenInfo类型的结果
	public MyBatisCursorItemReader<CourtOpenInfo> reader() {
        return new MyBatisCursorItemReaderBuilder<CourtOpenInfo>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.example.demo.mapper.CourtOpenInfoMapper.getItemCountByCourt")
                .build();
    }

	@Bean
	public ItemProcessor<CourtOpenInfo, CourtOpenInfo> processor() {
//		使用匿名内部类的方式配置processor
		return new ItemProcessor<CourtOpenInfo, CourtOpenInfo>() {
	        @Override
	        public CourtOpenInfo process(CourtOpenInfo courtOpenInfo) throws Exception {

	            // 将 CourtOpenInfo 转换为 HashMap，实现你的转换逻辑
//	            HashMap<String, Integer> courtInfosCountMap = new HashMap<>();
//	            courtInfosCountMap.put("TestCourt1", 10);
//	            courtInfosCountMap.put("TestCourt2", 20);
//        		courtInfosCountMap.put(courtOpenInfo.getCourtInfo().getCourtName(),courtOpenInfo.getCountInfos());
//        		System.out.println(courtOpenInfo.getCourtInfo().getCourtName());
//        		System.out.println(courtOpenInfo);
//        		System.out.println("__________________");
//        		System.out.println(courtInfosCountMap);

//	        	这里不用对数据做修改，直接返回查询的结果即可
	            return courtOpenInfo;
	        }
	    };
	}

	@Bean
	public ItemWriter<CourtOpenInfo> writer() {
		return new FlatFileItemWriterBuilder<CourtOpenInfo>()
//				writer的名字
				.name("toFile")
                .resource(new FileSystemResource("D:/test/output.txt")) // 修改为你希望保存文件的路径
                .lineAggregator(new DelimitedLineAggregator<CourtOpenInfo>() {
                    {
//                    	生成的文件中，字段的分隔符
                        setDelimiter(",");
                        setFieldExtractor(new BeanWrapperFieldExtractor<CourtOpenInfo>() {
                            {
                                setNames(new String[]{"courtInfo.courtName", "countInfos"}); // 与查询的结果CourtOpenInfo向对应的属性名
                            }
                        });
                    }
                })
                .build();
	}

//	private class MyJobListener extends JobExecutionListenerSupport {
//		@Override
//		public void afterJob(org.springframework.batch.core.JobExecution jobExecution) {
////			Integer rowCount = courtOpenInfoMapper.getRowCount();
////			System.out.println("Total row count: " + rowCount);
//		}
//	}

}
