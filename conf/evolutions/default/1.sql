# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table dvd (
  id                            integer auto_increment not null,
  usuario_id                    integer,
  nome                          varchar(255),
  descricao                     varchar(255),
  constraint pk_dvd primary key (id)
);

create table user (
  id                            integer auto_increment not null,
  nome                          varchar(255),
  senha                         varchar(255),
  constraint pk_user primary key (id)
);

create index ix_dvd_usuario_id on dvd (usuario_id);
alter table dvd add constraint fk_dvd_usuario_id foreign key (usuario_id) references user (id) on delete restrict on update restrict;


# --- !Downs

alter table dvd drop constraint if exists fk_dvd_usuario_id;
drop index if exists ix_dvd_usuario_id;

drop table if exists dvd;

drop table if exists user;

