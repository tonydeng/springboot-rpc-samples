namespace java com.github.tonydeng.demo.rpc.thrift


typedef i32 int
typedef i64 long

struct Note {
    1: string title,
    2: string author,
    3: list<string> tag,
    4: string content,
}

service NoteService {
    Note getNote(1: string title),

    Note createNote(4: string content),
}