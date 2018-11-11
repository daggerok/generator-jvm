package daggerok.events;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

@Slf4j
public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {

  @Override // 2018-10-17T06:47:19+03:00
  public ZonedDateTime unmarshal(final String string) throws Exception {
    if (null == string || "".equals(string.trim())) return null;
    final ZoneId zoneId = ZoneId.systemDefault();
    final DateTimeFormatter formatter = ISO_OFFSET_DATE_TIME.withZone(zoneId);
    return Try.of(() -> ZonedDateTime.parse(string, formatter))
              .getOrElseGet(throwable -> {
                log.error("cannot parse zoned date time: {}", throwable.getLocalizedMessage());
                return null;
              });
  }

  @Override // 2018-10-17T06:47:19.116+03:00[Europe/Kiev]
  public String marshal(final ZonedDateTime zonedDateTime) throws Exception {
    if (null == zonedDateTime) return "";
    return zonedDateTime.format(ISO_ZONED_DATE_TIME);
  }
}
