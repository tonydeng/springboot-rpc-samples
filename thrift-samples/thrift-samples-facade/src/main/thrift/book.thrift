namespace java com.github.tonydeng.demo.rpc.thrift.facade

//include "parent.thrift"
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

struct Note {
    1: string title,
    2: string desc,
    3: string author,
    4: list<string> tag,
}

service NoteService {
    Note getNote(1: string title),

    Note createNote(1: string desc),
}

service BookService {

    Book getBook(1: string isbn),

    list<Book>  createBooks(1: list<Book> books),
}