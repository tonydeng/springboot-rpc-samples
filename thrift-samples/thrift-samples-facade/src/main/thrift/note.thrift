namespace java com.github.tonydeng.demo.rpc.thrift


typedef i32 int
typedef i64 long

struct Note {
    1: string title,
    2: string content,
    3: string author,
    4: list<string> tag,
}

service NoteService {
    Note getNote(1: string title),

    Note createNote(2: string content),
}