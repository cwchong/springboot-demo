-- id should be a app-generated uuid instead of auto_incremented int
-- there is no equiv uuid type directly in mysql so it should be stored as varchar(36) or binary(16)
-- note varchar is not performant as fixed length
alter table person modify id varchar(36) not null;
