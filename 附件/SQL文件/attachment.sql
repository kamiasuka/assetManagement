use asset;
DROP TABLE IF EXISTS AttachmentList;
CREATE TABLE AttachmentList (
    id INT PRIMARY KEY auto_increment,
    assetId VARCHAR(255),
    Type VARCHAR(255),
    atName VARCHAR(255),
    Tip  VARCHAR(255),
    Status VARCHAR(255),
    url    varchar(255),
    updatedTime datetime
);
