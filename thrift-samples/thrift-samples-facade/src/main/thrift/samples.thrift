namespace java com.github.tonydeng.demo.rpc.thrift


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
    list<Book>  createBooks(1: list<Book> books),
}