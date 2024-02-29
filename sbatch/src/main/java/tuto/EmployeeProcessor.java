package tuto;

import org.springframework.batch.item.ItemProcessor;

import model.EmployeeDst;
import model.EmployeeDstList;
import model.EmployeeSrc;
import model.EmployeeSrcList;

// classe qui contient la logique métier pour transformer l'objet d'entrée en objet de sortie
public class EmployeeProcessor implements ItemProcessor<EmployeeSrcList, EmployeeDstList>{

	@Override
	public EmployeeDstList process(EmployeeSrcList item) throws Exception{
		return convertList (item);
	}
	private EmployeeDstList convertList (EmployeeSrcList employeeSrcList) {
		EmployeeDstList employeeDstList = new EmployeeDstList();
		for (int e=0; e< employeeSrcList.size(); e++) employeeDstList.add(convertOne(employeeSrcList.get(e)));
		return employeeDstList;
	}
	private EmployeeDst convertOne(EmployeeSrc employeeSrc){
		EmployeeDst employeeDst = new EmployeeDst();
		employeeDst.setFirstName(employeeSrc.getFirstName());
		employeeDst.setLastName(employeeSrc.getLastName());
		employeeDst.setMail(employeeSrc.getFirstName() + "." + employeeSrc.getLastName() + "@company.com");
		employeeDst.setPassword("radada");
		return employeeDst;
	}
}
