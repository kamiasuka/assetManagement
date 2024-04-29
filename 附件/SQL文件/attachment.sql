use asset;
DROP TABLE IF EXISTS Attachment;
CREATE TABLE Attachment (
    id INT PRIMARY KEY auto_increment,
    asset_code VARCHAR(255),
    type VARCHAR(255),
    asset_name VARCHAR(255),
    tip  VARCHAR(255),
    status VARCHAR(255),
    url    varchar(255),
    updatedTime datetime
);
