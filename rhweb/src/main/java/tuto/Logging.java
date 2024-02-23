package tuto;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
// https://docs.oracle.com/javase/8/docs/api/java/util/logging/SimpleFormatter.html
public class Logging extends SimpleFormatter{
	public String format(LogRecord record){
		return "coucou";
	}
}
