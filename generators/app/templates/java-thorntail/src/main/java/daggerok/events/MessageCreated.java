package daggerok.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;
import java.util.UUID;

import static java.time.ZonedDateTime.now;
import static lombok.AccessLevel.PACKAGE;

@Getter
@AllArgsConstructor(access = PACKAGE)
public class MessageCreated implements DomainEvent {

  @Getter
  final UUID aggregateId;
  final String message;

  @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
  //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  //@JsonSerialize(using = ZonedDateTimeSerializer.class)
  //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
  ZonedDateTime createdAt;

  public static MessageCreated of(final UUID uuid, final String message) {
    return new MessageCreated(uuid, message, now());
  }
}
