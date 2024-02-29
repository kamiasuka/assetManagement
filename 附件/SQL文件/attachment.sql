use asset;
CREATE TABLE AttachmentList (
    id INT PRIMARY KEY auto_increment,
    unit VARCHAR(255),
    BatchId VARCHAR(255),
    Type VARCHAR(255),
    Name VARCHAR(255),
    Tip  VARCHAR(255),
    Status VARCHAR(255),
    url    varchar(255),
    updatedTime datetime
);

CREATE TABLE Attachment (
    id INT PRIMARY KEY,
    assetId INT,
    attachmentUrl VARCHAR(255),
    FOREIGN KEY (assetId) REFERENCES AttachmentList(id)
);