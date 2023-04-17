DROP DATABASE IF EXISTS userwishlist;
CREATE SCHEMA userwishlist;
DROP TABLE IF EXISTS user;
CREATE TABLE `userwishlist`.`user` (
                                       `iduser` INT NOT NULL AUTO_INCREMENT,
                                       `username` VARCHAR(45) NOT NULL,
                                       `userpassword` VARCHAR(45) NOT NULL,
                                       PRIMARY KEY (`iduser`));
INSERT INTO `userwishlist`.`user` (`iduser`, `username`, `userpassword`) VALUES ('1', 'letsgo', 'whatever1234');
INSERT INTO `userwishlist`.`user` (`iduser`, `username`, `userpassword`) VALUES ('2', 'notgo', '');
ALTER TABLE `userwishlist`.`user`
    RENAME TO  `userwishlist`.`wuser` ;
