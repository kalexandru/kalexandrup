DoIt Spring - Boot/Security/Social/ Application


There is a need to create Database Tables for the user to be able to login via Facebook account and the application
should be created on the facebook developer site
Create the table in the database by executing the commands at this link:


http://docs.spring.io/spring-social/docs/1.1.0.RELEASE/reference/htmlsingle/#section_jdbcConnectionFactory




create table UserConnection (userId varchar(255) not null,
    providerId varchar(255) not null,
    providerUserId varchar(255),
    rank int not null,
    displayName varchar(255),
    profileUrl varchar(512),
    imageUrl varchar(512),
    accessToken varchar(512) not null,
    secret varchar(512),
    refreshToken varchar(512),
    expireTime bigint,
    primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);


// and then execute

create unique index UserConnectionProviderUser on UserConnection(providerId, providerUserId);