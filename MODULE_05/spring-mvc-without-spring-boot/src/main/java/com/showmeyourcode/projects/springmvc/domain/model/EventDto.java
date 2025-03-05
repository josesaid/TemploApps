package com.showmeyourcode.projects.springmvc.domain.model;

import com.showmeyourcode.projects.springmvc.domain.annotation.PresentOrFutureNotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long eventId;
    private Long userId;
    @Size(max = 255, min = 3, message = "{newEvent.location.size}")
    private String location;
    private String imagePath;
    @Size(max = 255, min = 3, message = "{newEvent.name.size}")
    private String name;
    private Timestamp createDate;
    @PresentOrFutureNotEmpty
    private Date targetDate;
    private int participants;
    @Size(max = 100, message = "{newEvent.desc.size}")
    private String description;
    private Long categoryId;
    private List<AppUserDto> users;
}
