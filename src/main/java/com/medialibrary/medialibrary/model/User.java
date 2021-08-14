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
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @ToString.Exclude @Getter @Setter private long id;

    @Column(name = "USERNAME")
    @Getter @Setter private String name;

}
