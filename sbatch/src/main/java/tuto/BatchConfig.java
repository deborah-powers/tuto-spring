package tuto;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.EmployeeDstList;
import model.EmployeeSrcList;

@EnableBatchProcessing
@Configuration // ma classe est un bean de configuration
public class BatchConfig{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobA(CompletionListener employeeListener, Step stepA) {
        return this.jobBuilderFactory.get("jobA").listener(employeeListener).start(stepA).build();
    }
	@Bean
    public Step stepA(EmployeeReader employeeReader, EmployeeWriter employeeWriter) {
        return this.stepBuilderFactory.get("stepA").<EmployeeSrcList, EmployeeDstList> chunk(2).reader(employeeReader)
                .processor(new EmployeeProcessor()).writer(employeeWriter).build();
    }
}
