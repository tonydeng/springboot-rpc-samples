# Armeria介绍

## 基本介绍

`Armeria`是一个开源的异步`HTTP/2` `RPC/REST`客户端/服务器库，构建于`Java 8`、`Netty`、`Thrift`和`gRPC`之上。它的主要目标是帮助工程师构建使用`HTTP/2`作为会话层协议的高性能异步微服务。

`Armeria`由`Netty`作者`Trustin Lee`开发的`RPC`框架，也是我目前知道的唯一可以将`Thrift`生成文档的框架。

## 依赖

### Maven配置

#### 依赖版本配置

```xml
<properties>
  <armeria.version>0.73.0</armeria.version>
</properties>
```

#### 配置依赖

- Armeria依赖

```xml
<dependency>
    <groupId>com.linecorp.armeria</groupId>
    <artifactId>armeria</artifactId>
    <version>${armeria.version}</version>
</dependency>
```

- Thrift支持依赖

```xml
<dependency>
    <groupId>com.linecorp.armeria</groupId>
    <artifactId>armeria-thrift</artifactId>
    <version>${armeria.version}</version>
</dependency>
```

- gRPC支持依赖

```xml
<dependency>
    <groupId>com.linecorp.armeria</groupId>
    <artifactId>armeria-grpc</artifactId>
    <version>${armeria.version}</version>
</dependency>
```

- 使用retrofit2作为HTTP Client的依赖

```xml
<dependency>
    <groupId>com.linecorp.armeria</groupId>
    <artifactId>armeria-retrofit2</artifactId>
    <version>${armeria.version}</version>
</dependency>
```

- 更多Maven配置

[Setting up a ](https://line.github.io/armeria/setup-maven.html)

### Gradle配置

``` groovy
dependencies {
    ['armeria',
     'armeria-grpc',
     'armeria-thrift'
}
```

- 更多Gradle配置

[Setting up a project with Gradle](https://line.github.io/armeria/setup-gradle.html)

## 使用

基本使用以`Armeria + Thrift`为例。

> 如何使用Thrift，可以查看[Thrift介绍](thrift.md)来进行了解。

### 定义结构和接口

```thrift
namespace java com.github.tonydeng.demo.rpc.armeria.facade


typedef i32 int
typedef i64 long

enum BookType {
        BOOK = 0,
        NEWS_PAPER = 1,
        COMIC_BOOK = 2,
}

struct Book {
    1: string ISBN,
    2: string title,
    3: string author,
    4: int page,
    5: list<string> keyword,
}

service BookService {

    Book getBook(1: string isbn),

    list<Book>  createBooks(1: list<Book> books),
}
```

### 实现生产者

```java
@Service
public class BookServiceProducer implements BookService.Iface {
    @Override
    public Book getBook(String isbn) {
        if (Strings.isNullOrEmpty(isbn)) {
            isbn = generateIsbn();
        }

        return new Book(isbn, "Armeria Samples", "Tony", 100,
                Lists.newArrayList("RPC", "Armeria"));
    }

    @Override
    public List<Book> createBooks(List<Book> books) {
        if (CollectionUtils.isEmpty(books)) {
            books = Lists.newArrayList();
        }

        for (Book b : books) {
            b.setISBN(generateIsbn());
        }
        return books;
    }
}
```

### 启动服务

```java
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.github.tonydeng.demo.rpc.armeria.producer"})
public class ArmeriaApplication {
    static final int PORT = 9000;

    public static void main(String[] args) {
        log.info("init armeria application.......");
        SpringApplication.run(ArmeriaApplication.class, args);
        final ServerBuilder sb = new ServerBuilder();

        sb.service("/book", THttpService.of(new BookServiceProducer(),
                ThriftSerializationFormats.COMPACT));
        sb.port(PORT, SessionProtocol.HTTP);
        sb.serviceUnder("/docs/", new DocService());
        sb.build().start().join();
        log.info("ArmeriaApplication Start.......");
    }
}
```

### 实现消费者

```java
@Slf4j
@Service("BookService")
@SpringBootConfiguration
public class BookServiceConsumer implements BookService.Iface {
    private static final String BOOK_URI = COMPACT.uriText()
            + "+http://127.0.0.1:9000/book";


    private static final BookService.Iface bookService =
            Clients.newClient(BOOK_URI, BookService.Iface.class);

    @Override
    public Book getBook(String isbn) throws TException {
        return bookService.getBook(isbn);
    }

    @Override
    public List<Book> createBooks(List<Book> books) throws TException {
        return bookService.createBooks(books);
    }

}
```

> 更多内容请查看[armeria-samples](https://github.com/tonydeng/springboot-rpc-samples/tree/master/armeria-samples)
