package com.jakedelivery.db.account;

import com.jakedelivery.db.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "account")
@Entity
public class AccountEntity extends BaseEntity {
}
