const express = require("express");
const fs = require("fs");
const path = require("path");

const app = express();
const PORT = 4000;

app.get("/download/:filename", (req, res) => {
  const { filename } = req.params;

  // 정규표현식을 사용하여 입력값을 검증
  const isValidFormat =
    /^file ((\()c=[1-9]\d{0,1}(\))|(\{)c=[1-9]\d{0,1}(\})) ((\()d=[1-9]\d{0,1}(\))|(\{)d=[1-9]\d{0,1}(\})).txt$/.test(
      filename
    );

  if (isValidFormat) {
    const cValue = Number(filename.match(/c=(\d{1,2})/)[1]);
    const dValue = Number(filename.match(/d=(\d{1,2})/)[1]);

    if (cValue >= 1 && cValue <= 60 && dValue >= 1 && dValue <= 50) {
      const filepath = path.join(__dirname, "data", filename);

      // 파일이 실제로 존재하는지 확인합니다.
      fs.access(filepath, fs.constants.F_OK, (err) => {
        if (err) {
          res.status(404).send("File not found");
        } else {
          // 파일이 존재하면, 사용자에게 다운로드를 제공합니다.
          res.download(filepath, (err) => {
            if (err) {
              res.status(500).send("Error downloading the file");
            }
          });
        }
      });
    } else {
      res.status(400).send("Invalid c or d value");
    }
  } else {
    res.status(400).send("Invalid filename format");
  }
});

app.listen(PORT, () => {
  console.log(`server is listening at localhost:${PORT}`);
});
