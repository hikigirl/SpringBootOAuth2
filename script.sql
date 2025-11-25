-- drop table tblTagging;
-- drop table tblTag;
-- drop table tblComment;
-- drop table tblBoard;
-- drop table tblUserInfo;
-- drop table tblUser;

create table tblUser (
    seq number primary key, --다양한 OAuth2 프로바이더를 사용한다면 별도의 PK를 두는게 좋다.
    username varchar2(100) unique not null, --내부 아이디
    name varchar2(50) not null, --사용자명
    email varchar2(100) unique not null,
    role varchar2(50) not null,
    provider varchar2(50) not null, --프로바이더(google, naver, daum 등)
    providerid varchar2(100) not null --각 OAuth2 공급자에서 발급하는 고유 사용자 ID
);
create sequence seqUser;

commit;

select * from tblUser;