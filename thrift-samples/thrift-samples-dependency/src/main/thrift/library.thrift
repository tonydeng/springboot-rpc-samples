namespace java com.github.tonydeng.demo.rpc.thrift

include "book.thrift"

service LibraryService {

    book.Book getBookByIsbn(1: string isbn),

    book.Book getBookByKeyword(1: string keyword),

    bool addBooks(1: list<book.Book> books),
}