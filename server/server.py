from email import header
from wsgiref import headers
import requests
from flask import Flask
from flask import request
app = Flask(__name__)


@app.route('/getaddr')
def getaddr():
    remote_ip = request.environ['REMOTE_ADDR']
    url = 'https://sp1.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query={}&resource_id=5809'.format(
        remote_ip)
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36'}

    return requests.get(url=url, headers=headers).text


@app.route('/ip')
def ip():
    return '{\n  "origin": "'+request.environ['REMOTE_ADDR']+'",\n}'

@app.route('/get',methods=['GET'])
def get():
    return str(request.headers)

if __name__ == '__main__':
    app.run(debug=False, port=7788, host='0.0.0.0')
