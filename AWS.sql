drop role customer_user;
create role customer_user identified by customer;
grant select on business to customer_user;
grant select on customer to customer_user;
grant update on customer to customer_user;
grant select on review to customer_user;
grant insert on review to customer_user;
grant select on menuitem to customer_user;
grant select on orders to customer_user;
grant insert on orders to customer_user;
grant update on orders to customer_user;
grant select on includes to customer_user;
grant insert on includes to customer_user;
grant select on postalcode to customer_user;
grant insert on postalcode to customer_user;
grant select on location to customer_user;
grant insert on location to customer_user;
grant select on located to customer_user;
grant select on residesin to customer_user;
grant insert on residesin to customer_user;
grant delete on residesin to customer_user;
grant select on reservation to customer_user;
grant insert on reservation to customer_user;
grant delete on reservation to customer_user;
grant select on reply to customer_user;
grant insert on bigspenders to customer_user;

drop role owner_user;
create role owner_user identified by owner;
grant select on businessowner to owner_user;
grant select on business to owner_user;
grant insert on business to owner_user;
grant update on business to owner_user;
grant delete on business to owner_user;
grant select on customer to owner_user;
grant select on review to owner_user;
grant select on menuitem to owner_user;
grant insert on menuitem to owner_user;
grant update on menuitem to owner_user;
grant delete on menuitem to owner_user;
grant select on orders to owner_user;
grant select on includes to customer_user;
grant select on postalcode to owner_user;
grant insert on postalcode to owner_user;
grant select on location to owner_user;
grant insert on location to owner_user;
grant select on located to owner_user;
grant insert on located to owner_user;
grant delete on located to owner_user;
grant select on residesin to owner_user;
grant select on reservation to owner_user;
grant select on reply to owner_user;
grant insert on reply to owner_user;
grant select on bigspenders to owner_user;

DROP PUBLIC SYNONYM business;
DROP PUBLIC SYNONYM businessowner;
DROP PUBLIC SYNONYM customer;
DROP PUBLIC SYNONYM review;
DROP PUBLIC SYNONYM menuitem;
DROP PUBLIC SYNONYM orders;
DROP PUBLIC SYNONYM includes;
DROP PUBLIC SYNONYM postalcode;
DROP PUBLIC SYNONYM location;
DROP PUBLIC SYNONYM located;
DROP PUBLIC SYNONYM residesin;
DROP PUBLIC SYNONYM reservation;
DROP PUBLIC SYNONYM reply;
DROP  PUBLIC SYNONYM bigspenders;
CREATE PUBLIC SYNONYM business for srv2691.business;
CREATE PUBLIC SYNONYM businessowner for srv2691.businessowner;
CREATE PUBLIC SYNONYM customer for srv2691.customer;
CREATE PUBLIC SYNONYM review for srv2691.review;
CREATE PUBLIC SYNONYM menuitem for srv2691.menuitem;
CREATE PUBLIC SYNONYM orders for srv2691.orders;
CREATE PUBLIC SYNONYM includes for srv2691.includes;
CREATE PUBLIC SYNONYM postalcode for srv2691.postalcode;
CREATE PUBLIC SYNONYM location for srv2691.location;
CREATE PUBLIC SYNONYM located for srv2691.located;
CREATE PUBLIC SYNONYM residesin for srv2691.residesin;
CREATE PUBLIC SYNONYM reservation for srv2691.reservation;
CREATE PUBLIC SYNONYM reply for srv2691.reply;
CREATE PUBLIC SYNONYM bigspenders for srv2691.bigspenders;

drop user reggie;
create user reggie identified by registration;
grant create session to reggie with admin option;
grant insert on businessowner to reggie;
grant insert on customer to reggie;
grant customer_user to reggie with admin option;
grant owner_user to reggie with admin option;
grant alter user to reggie;

drop user billsmith1025_owner;
drop user a123amycampbell_owner;
drop user samsonthegreat_owner;
drop user helloitsme123_owner;
drop user ilovefriedchicken_owner;
create user billsmith1025_owner identified by 12345;
grant owner_user to billsmith1025_owner;
grant create session to billsmith1025_owner;
create user a123amycampbell_owner identified by 12345;
grant owner_user to a123amycampbell_owner;
grant create session to a123amycampbell_owner;
create user samsonthegreat_owner identified by 12345;
grant owner_user to samsonthegreat_owner;
grant create session to samsonthegreat_owner;
create user helloitsme123_owner identified by 12345;
grant owner_user to helloitsme123_owner;
grant create session to helloitsme123_owner;
create user ilovefriedchicken_owner identified by 12345;
grant owner_user to ilovefriedchicken_owner;
grant create session to ilovefriedchicken_owner;

drop user henry00123_customer;
drop user angrytim_customer;
drop user sarahgibson92_customer;
drop user joeiscool_customer;
drop user foodiefoodie_customer;
create user henry00123_customer identified by 12345;
grant customer_user to henry00123_customer;
grant create session to henry00123_customer;
create user angrytim_customer identified by 12345;
grant customer_user to angrytim_customer;
grant create session to angrytim_customer;
create user sarahgibson92_customer identified by 12345;
grant customer_user to sarahgibson92_customer;
grant create session to sarahgibson92_customer;
create user joeiscool_customer identified by 12345;
grant customer_user to joeiscool_customer;
grant create session to joeiscool_customer;
create user foodiefoodie_customer identified by 12345;
grant customer_user to foodiefoodie_customer;
grant create session to foodiefoodie_customer;