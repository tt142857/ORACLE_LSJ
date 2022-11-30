package kr.letech.study02.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class DateUtils {

	private static DateUtils instance;

	private String formatDate;
	private SimpleDateFormat simpleDateFormat;

	private DateUtils() {
		simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd");
		formatDate = simpleDateFormat.format(new Date());
	}

	public static DateUtils getInstance() {
		if(instance == null) {
			instance = new DateUtils();
		}
		return instance;
	}
}