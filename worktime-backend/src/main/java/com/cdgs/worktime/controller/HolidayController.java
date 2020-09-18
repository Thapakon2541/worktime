package com.cdgs.worktime.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tempuri.ISWebService.ISService.Holiday;
import org.tempuri.ISWebService.ISService.ISServiceSoapProxy;

import com.cdgs.worktime.dto.HolidayDto;
import com.cdgs.worktime.dto.SideworkDateToSting;
import com.cdgs.worktime.util.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/holiday")
@Slf4j
public class HolidayController {

	@GetMapping(path = "/{year}/{empNo}")
	private ResponseEntity<ResponseDto<HolidayDto>> getHoliday(@PathVariable(value = "year") Integer year,
			@PathVariable(value = "empNo") String empNo) {
		ResponseDto<HolidayDto> res = new ResponseDto<HolidayDto>();
		try {
			Locale LOCALE_TH = new Locale("th", "TH");
			SimpleDateFormat convertDateToCallService = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

			Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
			ArrayList<HolidayDto> holidaysList = new ArrayList<HolidayDto>();

			calendar.set(Calendar.DATE, 1);
			calendar.set(Calendar.MONTH, 0);
			calendar.set(Calendar.YEAR, year);

			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			Date startDate = calendar.getTime();

			calendar.add(Calendar.MONTH, 12);
			calendar.add(Calendar.DATE, -1);

			Date endDate = calendar.getTime();
			String convertTextDateStart = convertDateToCallService.format(startDate);
			String convertTextDateEnd = convertDateToCallService.format(endDate);

			ISServiceSoapProxy ispo = new ISServiceSoapProxy();
<<<<<<< HEAD
			Holiday[] holidayResults = ispo.getISServiceSoap().getHoliday(empNo, convertTextDateStart,
					convertTextDateEnd);

			SimpleDateFormat holidayDate = new SimpleDateFormat("yyyy-MM-dd");

			for (Holiday holidayResult : holidayResults) {
				holidaysList.add(new HolidayDto(holidayResult.getHolidayEngName(),
						holidayDate.parse(holidayResult.getHolidayDate())));
			}

			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(holidaysList);
			res.setCode(201);
			return new ResponseEntity<ResponseDto<HolidayDto>>(res, HttpStatus.OK);
=======
			Holiday[] holidayResults = ispo.getISServiceSoap().getHoliday(empNo, convertTextDateStart,convertTextDateEnd);							
			
			List<HolidayDto> holidays = new ArrayList<HolidayDto>();

			try {
				for (Holiday holidayResult : holidayResults) {
					holidays.add(new HolidayDto(holidayResult.getHolidayDate(), holidayResult.getHolidayEngName()));		
				}
				res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
				res.setData(holidays);
				res.setCode(201);
				return new ResponseEntity<ResponseDto<HolidayDto>>(res, HttpStatus.OK);
			} catch (Exception e) {
				log.error(e.getMessage());
				res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
				res.setErrorMessage(e.getMessage());
				res.setCode(400);
				return new ResponseEntity<ResponseDto<HolidayDto>>(res, HttpStatus.BAD_REQUEST);
			}

>>>>>>> 864280f4362b3739eb08d6900d7a30c2d33c61cd
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<ResponseDto<HolidayDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
