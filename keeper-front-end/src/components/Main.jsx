import React, { useState } from "react";
import Header from "./Header";
import Footer from "./Footer";
import Note from "./Note";
import CreateArea from "./CreateArea";
import cookies from "js-cookie";

import axios from "axios";


function Main() {
  const [notes, setNotes] = useState([]);

  //retrive data
  axios.post("http://10.200.24.235:3000/content/list", {access_token: cookies.get("access_token")})
  .then(res => {
    if (res.data != null && res.data.length !== 0) {
      setNotes(res.data);
    }
  });


  function addNote(newNote) {

    axios.post("http://10.200.24.235:3000/content/save", {
      access_token: cookies.get("access_token"),
      title: newNote.title,
      content: newNote.content,
    }).then(res => {
      //handle error
    })


    setNotes(prevNotes => {
      return [...prevNotes, newNote];
    });
  };

  function deleteNote(id) {
    axios.post("http://10.200.24.235:3000/content/delete", {
      access_token: cookies.get("access_token"),
      title: notes[id].title,
      content: notes[id].content
    }).then(res=>{
      setNotes(prevNotes => {
        return prevNotes.filter((noteItem, index) => {
          return index !== id;
        });
      });
    });
  }
  return (
    <div>
      <Header />
      <CreateArea onAdd={addNote} />
      {notes.map((noteItem, index) => {
        return (
          <Note
            key={index}
            id={index}
            title={noteItem.title}
            content={noteItem.content}
            onDelete={deleteNote}
          />
        );
      })}
      <Footer />
    </div>
  );
}

export default Main;
