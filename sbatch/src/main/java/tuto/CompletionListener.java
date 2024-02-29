package tuto;

import org.springframework.batch.core.BatchStatus;
/* actions à faire avant ou après le lancement du batch */
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CompletionListener implements JobExecutionListener{
	@Value("${data.dst}")
	private String dataFileName;

	@Override
	public void beforeJob(JobExecution jobExecution){ log.info("le batch se lance."); }

	@Override
	public void afterJob(JobExecution jobExecution){
		if (jobExecution.getStatus() == BatchStatus.COMPLETED)
			log.info("le batch à réussi !\n\t\t\tregardez ce qui est affiché dans "+ dataFileName);
		else if (jobExecution.getStatus() == BatchStatus.FAILED)
			log.info("le batch à échoué");
		else if (jobExecution.getStatus() == BatchStatus.ABANDONED)
			log.info("le batch est abandonné");
		else
			log.info("le batch est dans un état inconnu, " + jobExecution.getStatus());
	}
}
