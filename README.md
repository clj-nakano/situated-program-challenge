# situated-program-challenge
Rich HickeyのClojure/Conj2017キーノートの考察として、外界の変化を言語ごとにどれだけ上手く扱えるか比較する

## Situated Programに求められる要件
* 複数のシステムから構成されていること
* 他者によって書かれたライブラリを含んでいること
* データベースを使用していること
* 時間の経過とともに変わる要件に柔軟に対応できること

## リポジトリ管理
* 本リポジトリを自分のアカウントにフォークし、選択した言語で実装し、ソースコードをコミットする。
* 本リポジトリにプルリクをする必要はなく、発表時はフォークしたリポジトリを用いる。

## 初期仕様
* `version1` gitブランチに保持すること
### システム１ RESTサーバ
* 本リポジトリ内で定義された `swagger-spec-v1.json` に基づいたエンドポイントを持つ。
* HTTPリクエストを処理し、レスポンスを返す。
* POSTデータをPostgreSQLに格納し、GETリクエストはPostgreSQLから読み出す。
* テーブルスキーマは本プロジェクト内で `psql-v1.sql` として定義されている。

### システム２ RESTクライアント
* コマンドラインプログラムであり、サーバURL, コマンド, 引数をとり、標準出力にデータを表示し、終了する。
* イメージ

    ```
    java -jar situated-program-scala.jar http://localhost:3000/person POST key1=value1 key2=value2
    ```

* システム１と通信し、RESTful POST/GETエンドポイントを呼び出す。
* 本システムは、POSTとGETという２つのコマンドをコマンドライン引数にとる。
* POSTコマンドは、コマンドライン引数から与えられたデータをPOSTで送信する。
* GETはシステム１からデータを取得し、標準出力に表示する。

### PostgresSQLデータベース
* PostgresSQLを起動する`docker-compose.yml`が本リポジトリで提供されているので、それを利用してサーバを起動する。
* PSQLは所与の環境として考え、比較の対象外である。

## 仕様変更
* `version2` gitブランチに保持すること

### RESTエンドポイントの変更
* 本リポジトリ内で定義された `swagger-spec-v2.json` に合わせ、エンドポイントを修正する。

### DBスキーマの変更
* `psql-v2.sql` がスキーマを変更するDDLを含んでいる。それに合わせてコードを修正する。


## 評価ポイント
* 初期仕様におけるコードサイズ
    * サードパーティライブラリを最大限活用して、自前で書くコードを最小限に抑える
* 仕様変更にかかる変更量を、`version1` `version2` ブランチ間のdiffファイルサイズで判定
* 開発者にとって修正のやりさすさ、変更のわかりやすさも主観的に判定

## 開発環境
* docker, docker-compose 
    * MacOS: https://docs.docker.com/docker-for-mac/install/
    * Windows: https://docs.docker.com/docker-for-windows/install/
    * Linux:　https://github.com/docker/compose/releases
* 自分の選択した言語に必要な開発環境は追記してください。
    * dockerを使う必要はありません。

### Postgresqlサーバの起動

```
docker-compose up 
```
* Daemonとして走らせたい場合は `-d` オプションを追加。
* 5432番ポートでソケットを開いているので、ホストOSのpsql、または他のSQLツールで接続可能。
* 接続情報は `docker-compose.yml` を参照のこと。
* Dockerからpsqlを利用することも可能。

```
docker run -it -e PGPASSWORD=password123 -v $PWD:/project postgres:9.6 psql -U meetup -h docker.for.mac.localhost meetup
```

### DBスキーマの設定

* 仕様バージョン１のスキーマを設定するには、下記コマンドを実行。

```
lein migratus up 20171128151001
```

### swagger.jsonの使用方法
* [swagger editor](https://swagger.io/swagger-editor/)でswagger-spec-v1.jsonを開く。
* スペックをUIで確認。
* Swagger codegenでコード生成することも可能だが、必ずしもコードのクオリティが高くないので、見極めが必要。



