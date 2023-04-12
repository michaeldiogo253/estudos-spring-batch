package estudosbatchproject.estudosbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfigNovaVersao {

	@Bean
	public Job job(JobRepository jobRepository, Step step) {

		return new JobBuilder("job", jobRepository).start(step)
		                                           .build();
	}

	@Bean
	public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

		return new StepBuilder("step",
		                       jobRepository)
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
			                                     System.out.println("Ola Mundo");
			                                     return RepeatStatus.FINISHED;
		                                     }, transactionManager)
		                                     .build();
	}
}
