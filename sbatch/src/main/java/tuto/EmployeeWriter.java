package tuto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import model.EmployeeDstList;

@Slf4j
@Component
public class EmployeeWriter implements ItemWriter<EmployeeDstList>{
	@Value("${data.folder}")
	private String dataFolder;
	@Value("${data.dst}")
	private String dataFileName;

	@Override
	public void write(List<? extends EmployeeDstList> items) throws Exception{
		writeEmployeeFile (dataFileName, items.get(0));
	}

	private void writeEmployeeFile(String fileName, EmployeeDstList employees){
		try {
		FileWriter fileWriter = new FileWriter(dataFolder + fileName);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for (int l = 0; l < employees.size(); l++)
			bufferedWriter.write(employees.get(l).toCsvLine());
		bufferedWriter.close();
	} catch (IOException exception) {
		log.error("le fichier est introuvable ou illisible: " + fileName);
	}}
}
