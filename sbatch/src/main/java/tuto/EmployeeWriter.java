package tuto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import model.EmployeeDstList;
import utils.DataFileProperties;

@Slf4j
@Component
public class EmployeeWriter implements ItemWriter<EmployeeDstList>{
	// première méthode pour récupérer les données de application.properties
	@Value("${data.folder}")
	private String dataFolder;
	@Value("${data.dst}")
	private String dataFileName;
	// deuxième méthode pour récupérer les données de application.properties
	@Autowired
	private DataFileProperties dataFile;

	@Override
	public void write(List<? extends EmployeeDstList> items) throws Exception{
		writeEmployeeFile (items.get(0));
	}

	private void writeEmployeeFile(EmployeeDstList employees){
		try {
		FileWriter fileWriter = new FileWriter(dataFile.getDstPath());
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for (int l = 0; l < employees.size(); l++)
			bufferedWriter.write(employees.get(l).toCsvLine());
		bufferedWriter.close();
	} catch (IOException exception) {
		log.error("le fichier est introuvable ou illisible: " + dataFile.getDst());
	}}
}
