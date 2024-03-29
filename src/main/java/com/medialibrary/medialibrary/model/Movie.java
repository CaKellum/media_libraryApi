package com.medialibrary.medialibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Scope("prototype")
@Entity
@Table(name = "MOVIE")
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Movie{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @ToString.Exclude @Getter @Setter private long id;

    @Column(name = "TITLE")
    @Getter @Setter private String title;

    @Column(name = "MEDIA_FORMAT")
    @Getter @Setter private String format;

    @Column(name = "USER_ID")
	@Getter @Setter private long userId;
}