package utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.CoreConstants;

/* https://logback.qos.ch/manual/layouts.html
 * https://stackoverflow.com/questions/14168684/creating-a-custom-layout-in-logback */
public class DebLayout extends PatternLayout{
	private boolean useDate = false;
	private boolean errorSmall = true;
	private String dateWday = "yyyy-MM-dd HH:mm:ss";
	private String dateWtday = "HH:mm:ss";
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateWtday);

	@Override
	public String doLayout(ILoggingEvent event){
		StringBuffer sbuf = new StringBuffer(128);
		if (useDate)
			sbuf.append(getDate(event.getTimeStamp()));
		sbuf.append(getLevel(event.getLevel().levelStr));
//		sbuf.append(getLoggerName(event.getLoggerName()));
		sbuf.append(getLines(event.getCallerData()));
		sbuf.append("\t");
		sbuf.append(getMessage(event.getFormattedMessage()));
		if (event.getThrowableProxy() != null)
			sbuf.append(getError(event.getThrowableProxy()));
		sbuf.append(CoreConstants.LINE_SEPARATOR);
		return sbuf.toString();
	}

	private String getDate(Long timeStamp){
		Timestamp time = new Timestamp(timeStamp);
		LocalDateTime date = time.toLocalDateTime();
		String dateString = date.format(formatter) + " ";
		return dateString;
	}

	private String getLevel(String level){
		while (level.length() < 6)
			level = level + " ";
		return level;
	}

	private String getMessage(String message){
		message = message.replaceAll("threw exception \\[", "threw exception\n[");
		return message;
	}

	private String getLines(StackTraceElement[] lines){
		int pos = 0;
		while (excludeLine(lines[pos]) && pos < lines.length)
			pos = pos + 1;
		return getLine(lines[pos]);
	}

	private String getLine(StackTraceElement line){
		String data = getLoggerName(line.getClassName()) + "." + line.getMethodName() + ": " + line.getLineNumber();
		return data;
	}

	private boolean excludeLine(StackTraceElement line){
		boolean exclude = false;
		if (line.getMethodName().substring(0, 3).equals("log"))
			exclude = true;
		else if (line.getClassName().contains("JDKLog"))
			exclude = true;
		else if (line.getMethodName().contains("init>"))
			exclude = true;
		else if (line.getClassName().contains("Context"))
			exclude = true;
		else if (line.getMethodName().contains("Context"))
			exclude = true;
		else if (line.getMethodName().contains("start"))
			exclude = true;
		else if (line.getMethodName().equals("run"))
			exclude = true;
		return exclude;
	}

	private String getLoggerName(String fullName){
		if (fullName.contains("tuto."))
			return fullName;
		else {
			fullName = fullName.replace("org.", "");
			fullName = fullName.replace("fr.", "");
			fullName = fullName.replace("com.", "");
			String[] splitName = fullName.split("\\.");
			String newName = "";
			int i = 0;
			for (; i < splitName.length - 1; i++)
				newName = newName + splitName[i].charAt(0) + ".";
			newName = newName + splitName[i];
			return newName;
		}
	}

	private String getError(IThrowableProxy erreur){
		String trace = "";
		if (errorSmall) {
			for (int t = 0; t < erreur.getStackTraceElementProxyArray().length; t++) {
				if (erreur.getStackTraceElementProxyArray()[t].getStackTraceElement().getClassName().contains("tuto."))
					trace = trace + "\n\t"
							+ erreur.getStackTraceElementProxyArray()[t].getStackTraceElement().getClassName() + " "
							+ erreur.getStackTraceElementProxyArray()[t].getStackTraceElement().getMethodName() + ": "
							+ erreur.getStackTraceElementProxyArray()[t].getStackTraceElement().getLineNumber();
			}
		} else {
			for (int t = 0; t < erreur.getStackTraceElementProxyArray().length; t++) {
				// if
				// (erreur.getStackTraceElementProxyArray()[t].getStackTraceElement().getClassName().contains("tuto."))
				trace = trace + "\n\t"
						+ erreur.getStackTraceElementProxyArray()[t].getStackTraceElement().getClassName() + " "
						+ erreur.getStackTraceElementProxyArray()[t].getStackTraceElement().getMethodName() + ": "
						+ erreur.getStackTraceElementProxyArray()[t].getStackTraceElement().getLineNumber();
			}
		}
		return trace;
	}
}