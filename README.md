# jerseysample

JerseyでブラウザからURLを叩くと、とりあえず何かを返すAPIを作ってみた。

以下はURLと何がかえってくるかをリストアップした。



##http://localhost:8080/jerseysample2/api/

-> Without path param.


##http://localhost:8080/jerseysample2/api/hello

-> Hello Jersey.


##http://localhost:8080/jerseysample2/api/hoge

-> path : hoge


##http://localhost:8080/jerseysample2/api/query/string?query=fuga

-> queryString : fuga


##http://localhost:8080/jerseysample2/api/query/integer?query=3

-> queryInteger : 3


##http://localhost:8080/jerseysample2/api/query/integer?query=funya

-> not found(404)

クエリパラメータは数値に限定されるため、該当処理が見つからないと解釈される。


##http://localhost:8080/jerseysample2/api/json

-> {"personsDataDto":[{"name":"Tom","age":56,"address":"Tokyo","hobby":"fishing"},{"name":"David","age":18,"address":"Osaka","hobby":"running"},{"name":"Jef","age":32,"address":"Okinawa","hobby":"cooking"}]}

