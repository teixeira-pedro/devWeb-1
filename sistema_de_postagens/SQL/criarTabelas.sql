/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  pedroteixeira
 * Created: 13/06/2019
 */


CREATE TABLE "Comment" (
    "id_c" INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    "text" VARCHAR(8000),
    "nickname" VARCHAR(200) NOT NULL,
    "pst" INTEGER NOT NULL
);

CREATE TABLE "Usr" (
    "nickname" VARCHAR(200) NOT NULL PRIMARY KEY,
    "senha" VARCHAR(40) NOT NULL,
    "avatar" VARCHAR(8000) DEFAULT 'https://zcoin.io/wp-content/uploads/2017/01/blank-avatar-e1484377033610.png',
    "nome" VARCHAR(200) NOT NULL
);

CREATE TABLE "Post" (
    "id" INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    "title" VARCHAR(20),
    "text" VARCHAR(8000),
    "autor"  VARCHAR(200) NOT NULL
);


ALTER TABLE "Post" ADD FOREIGN KEY ("autor") REFERENCES "Usr" ;



ALTER TABLE "Comment" ADD FOREIGN KEY ("pst") REFERENCES "Post" ;

ALTER TABLE "Comment" ADD FOREIGN KEY ("nickname") REFERENCES "Usr" ;


/*CREATE TABLE Post(
    id          INTEGER             NOT NULL AUTO_INCREMENT,
    title       CHARACTER VARYING   ,
    text        CHARACTER VARYING   ,
    autor       CHARACTER VARYING   ,
    PRIMARY KEY(id),
    FOREIGN KEY autor REFERENCES Usr(nickname) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE Comment(
    id_c        INTEGER             NOT NULL AUTO_INCREMENT,
    title       CHARACTER VARYING   ,
    autor       CHARACTER VARYING   ,
    id_p        INTEGER             NOT NULL,
    FOREIGN KEY id_p REFERENCES Post(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY autor REFERENCES Usr(nickname) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY(id_c)
);

CREATE TABLE Usr(
    nickname    CHARACTER VARYING   NOT NULL,
    senha       CHARACTER VARYING   NOT NULL,
    nome        CHARACTER VARYING,
    PRIMARY KEY(login)
);
*/