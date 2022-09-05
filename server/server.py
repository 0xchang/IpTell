import database
from flask import request
from flask import Flask
import requests
from wsgiref import headers
from email import header
import time
app = Flask(__name__)


@app.route('/getaddr')
def getaddr():
    remote_ip = request.environ['REMOTE_ADDR']
    url = 'https://sp1.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query={}&resource_id=5809'.format(
        remote_ip)
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36'}

    result = database.select(remote_ip)
    if(len(result) == 0):
        # 插入数据并返回
        myvalue = requests.get(url=url, headers=headers).text
        database.insert(remote_ip, time.time(), myvalue=myvalue)
        return myvalue
    elif int(time.time()) - (result[0][1]) > 24*3600:
        # 更新数据并返回
        myvalue = requests.get(url=url, headers=headers).text
        database.update(remote_ip, time.time(), myvalue=myvalue)
        return myvalue
    else:
        return result[0][2]


@app.route('/ip')
def ip():
    return '{\n  "origin": "'+request.environ['REMOTE_ADDR']+'",\n}'


@app.route('/get', methods=['GET'])
def get():
    return str(request.headers)


if __name__ == '__main__':
    database.table_create()

    app.run(debug=False, port=7788, host='0.0.0.0')
