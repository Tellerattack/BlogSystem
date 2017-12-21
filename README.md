## 私人博客系统
## API

### 博主博文检索
- 接口地址：http://localhost:8080/bloggerName/blog
- 返回格式：json
- 请求方式：get
- 请求示例：http://localhost:8080/duan/blog?sort=collect_coUNT&order=asc
- 说明：
bloggerName为博主用户名，该参数必不可少；
如果请求参数都不指定，则默认返回按博文热度（浏览次数）降序，不限定类别和标签的前10条数据。
- 请求参数说明：
<table>
<tr>
<th>名称</th>
<th>类型</th>
<th>必填</th>
<th>说明</th>
</tr>
<tr>
<td>cids</td>
<td>string</td>
<td>否</td>
<td>博主的文章类别id，可指定在多个类别，用英文“,”间隔</td>
</tr>
<tr>
<td>lids</td>
<td>string</td>
<td>否</td>
<td>博主的标签id，可指定多个标签，用英文“,”间隔</td>
</tr>
<tr>
<td>kword</td>
<td>string</td>
<td>否</td>
<td>关键字</td>
</tr>
<tr>
<td>offset</td>
<td>int</td>
<td>否</td>
<td>结果集起始下标</td>
</tr>
<tr>
<td>rows</td>
<td>int</td>
<td>否</td>
<td>结果集行数</td>
</tr>
<tr>
<td>sort</td>
<td>string</td>
<td>否</td>
<td>结果集排序依据，参看<a>博文排序依据</a>查看有哪些可选字段</td>
</tr>
<tr>
<td>order</td>
<td>string</td>
<td>否</td>
<td>结果集排序顺序，从大到小为“desc”，从小到大为“asc”</td>
</tr>
</table>

- 返回参数说明
<table>
<tr>
<th>名称</th>
<th>类型</th>
<th>说明</th>
</tr>
<tr>
<td>code</td>
<td>int</td>
<td>返回码</td>
</tr>
<tr>
<td>msg</td>
<td>string</td>
<td>返回说明</td>
</tr>
<tr>
<td>data</td>
<td>string</td>
<td>返回结果集</td>
</tr>
</table>

- JSON返回示例
```json
{
    "code": 0,
    "msg": "success",
    "data": [
        {
            "id": 3,
            "categories": [
                {
                    "id": 5,
                    "bloggerId": 1,
                    "title": "VR",
                    "desc": "victurl",
                    "createDate": 1513190620000
                }
            ],
            "title": "这是标题",
            "summary": "摘要",
            "releaseDate": 1513221234000,
            "commentCount": 0,
            "viewCount": 23,
            "collectCount": 73,
            "likeCount": 0
        },
        {
            "id": 2,
            "categories": [
                {
                    "id": 1,
                    "bloggerId": 1,
                    "title": "编程语言",
                    "desc": "java c c++ ",
                    "createDate": 1513075612000
                },
                {
                    "id": 2,
                    "bloggerId": 1,
                    "title": "网络",
                    "desc": "TCP/IP，UDP，4-7",
                    "createDate": 1513075645000
                }
            ],
            "title": "性跟 dbcp 连接池的差不多",
            "summary": " testWhileIdle的判断依据，详细看testWhileIdle属性的说明",
            "releaseDate": 1513078005000,
            "commentCount": 0,
            "viewCount": 10,
            "collectCount": 101,
            "likeCount": 0
        }
    ]
}
```


License
============

    Copyright 2017 DuanJiaNing

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.


