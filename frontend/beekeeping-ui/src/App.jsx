import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';

const apiUrl = 'http://localhost:8080';

const styles = {
  container: {
    maxWidth: '1200px',
    margin: '0 auto',
    padding: '20px',
    fontFamily: 'Segoe UI, Tahoma, Geneva, Verdana, sans-serif',
    color: '#333',
  },
  header: {
    color: '#FFA500',
    textAlign: 'center',
    marginBottom: '30px',
    textShadow: '1px 1px 2px rgba(0,0,0,0.1)',
  },
  section: {
    backgroundColor: '#FFF9E6',
    borderRadius: '10px',
    padding: '20px',
    marginBottom: '30px',
    boxShadow: '0 4px 6px rgba(0,0,0,0.1)',
  },
  button: {
    backgroundColor: '#FFA500',
    color: 'white',
    border: 'none',
    padding: '10px 20px',
    borderRadius: '5px',
    cursor: 'pointer',
    fontSize: '16px',
    fontWeight: '600',
    transition: 'all 0.3s ease',
    margin: '5px',
  },
  input: {
    padding: '10px',
    borderRadius: '5px',
    border: '1px solid #ddd',
    margin: '5px',
    width: '200px',
    fontSize: '16px',
  },
  list: {
    listStyle: 'none',
    padding: 0,
  },
  listItem: {
    backgroundColor: 'white',
    padding: '15px',
    marginBottom: '10px',
    borderRadius: '5px',
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    boxShadow: '0 2px 4px rgba(0,0,0,0.05)',
    transition: 'all 0.3s ease',
  },
  formGroup: {
    marginBottom: '15px',
  },
  nav: {
    display: 'flex',
    justifyContent: 'center',
    marginBottom: '20px',
  },
  navLink: {
    margin: '0 10px',
    padding: '10px 15px',
    textDecoration: 'none',
    color: '#FFA500',
    fontWeight: '600',
    borderRadius: '5px',
    transition: 'all 0.3s ease',
  },
  homeContainer: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    height: '60vh',
  },
  homeImage: {
    maxWidth: '300px',
    marginBottom: '20px',
  },
  chatContainer: {
    display: 'flex',
    flexDirection: 'column',
    height: '400px',
  },
  chatMessages: {
    flex: 1,
    overflowY: 'auto',
    marginBottom: '10px',
    padding: '10px',
    backgroundColor: 'white',
    borderRadius: '5px',
  },
  message: {
    marginBottom: '10px',
    padding: '8px 12px',
    borderRadius: '5px',
  },
  userMessage: {
    backgroundColor: '#E3F2FD',
    alignSelf: 'flex-end',
  },
  aiMessage: {
    backgroundColor: '#FFF9E6',
    alignSelf: 'flex-start',
  },
};

function HomePage() {
  return (
    <div style={styles.container}>
      <div style={styles.homeContainer}>
        <img
          src="https://cdn-icons-png.flaticon.com/128/809/809052.png"
          alt="Пчела"
          style={styles.homeImage}
        />
        <h1 style={styles.header}>Hello Beekeeper!</h1>
        <Link to="/app">
          <button style={styles.button}>Go to journal</button>
        </Link>
      </div>
    </div>
  );
}

function AIChat() {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');

  // Text-to-speech
  const speak = (text) => {
    const utterance = new SpeechSynthesisUtterance(text);
    utterance.lang = 'en-US';
    window.speechSynthesis.speak(utterance);
  };

  // Launching speech recognition
  const startRecognition = () => {
    const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
    if (!SpeechRecognition) {
      alert('Your browser does not support speech recognition.');
      return;
    }

    const recognition = new SpeechRecognition();
    recognition.lang = 'en-US';
    recognition.interimResults = false;
    recognition.maxAlternatives = 1;

    recognition.onresult = (event) => {
      const transcript = event.results[0][0].transcript;
      setInput(transcript);
      sendMessage(transcript);
    };

    recognition.onerror = (event) => {
      console.error('Speech recognition error:', event.error);
    };

    recognition.start();
  };


  // Sending a message + receiving a response
  const sendMessage = async (rawInput) => {
    const messageText = typeof rawInput === 'string' ? rawInput : input;
    if (typeof messageText !== 'string' || !messageText.trim()) return;

    const userMessage = { text: messageText, sender: 'user' };
    setMessages((prev) => [...prev, userMessage]);
    setInput('');

    try {
      const response = await fetch(`${apiUrl}/ai?userInput=${encodeURIComponent(messageText)}`);
      const aiText = await response.text();
      setMessages((prev) => [...prev, { text: aiText, sender: 'ai' }]);
      speak(aiText); // Voice-over of the response
    } catch (error) {
      const errorMsg = 'An error occurred while receiving a response from the AI';
      setMessages((prev) => [...prev, { text: errorMsg, sender: 'ai' }]);
      speak(errorMsg);
    }
  };


  return (
    <div style={styles.section}>
      <h2>AI chat</h2>
      <div style={styles.chatContainer}>
        <div style={styles.chatMessages}>
          {messages.map((msg, index) => (
            <div
              key={index}
              style={{
                ...styles.message,
                ...(msg.sender === 'user' ? styles.userMessage : styles.aiMessage),
              }}
            >
              {msg.text}
            </div>
          ))}
        </div>
        <div style={{ display: 'flex' }}>
          <input
            style={{ ...styles.input, flex: 1 }}
            value={input}
            onChange={(e) => setInput(e.target.value)}
            placeholder="Enter your question..."
            onKeyDown={(e) => e.key === 'Enter' && sendMessage()}
          />
          <button style={styles.button} onClick={() => sendMessage()}>
            Send
          </button>
          <button style={{ ...styles.button, backgroundColor: '#8884ff' }} onClick={startRecognition}>
            🎤 Speak
          </button>
        </div>
      </div>
    </div>
  );
}

function AppContent() {
  
  const [hives, setHives] = useState([]);
  const [beeFamilies, setBeeFamilies] = useState([]);
  const [hiveId, setHiveId] = useState('');
  const [beeFamilyId, setBeeFamilyId] = useState('');
  const [hiveById, setHiveById] = useState(null);
  const [beeFamilyById, setBeeFamilyById] = useState(null);
  const [newHive, setNewHive] = useState({ 
    hiveName: '', 
    hiveType: '', 
    hiveMaterial: '', 
    framesPerBody: '' 
  });
  const [newBeeFamily, setNewBeeFamily] = useState({ 
    beeFamilyName: '', 
    beeFamilyType: '', 
    beeFamilyPower: '' 
  });

  const fetchAll = async (type) => {
    const res = await fetch(`${apiUrl}/${type}`);
    const data = await res.json();
    type === 'hive' ? setHives(data) : setBeeFamilies(data);
  };

  const fetchById = async (type, id) => {
    const res = await fetch(`${apiUrl}/${type}/${id}`);
    const data = await res.json();
    type === 'hive' ? setHiveById(data) : setBeeFamilyById(data);
  };

  const addItem = async (type, item) => {
    await fetch(`${apiUrl}/${type}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(item)
    });
    fetchAll(type);
  };

  const deleteById = async (type, id) => {
    await fetch(`${apiUrl}/${type}/${id}`, { method: 'DELETE' });
    fetchAll(type);
    if (type === 'hive') setHiveById(null);
    else setBeeFamilyById(null);
  };
  
  return (
    <div style={styles.container}>
      <nav style={styles.nav}>
        <Link to="/" style={styles.navLink}>Main Page</Link>
        <Link to="/app" style={styles.navLink}>Journal</Link>
      </nav>

      <h1 style={styles.header}>Beekeeper journal</h1>

      <div style={styles.section}>
        <h2>Hives</h2>
        <button 
          style={styles.button} 
          onClick={() => fetchAll('hive')}
        >
          🐝 Show all hives
        </button>
        
        <ul style={styles.list}>
          {hives.map(h => (
            <li key={h.id} style={styles.listItem}>
              <div>
                <strong>{h.hiveName}</strong> ({h.hiveType})<br />
                Material: {h.hiveMaterial}, Frames per body: {h.framesPerBody}
              </div>
              <button 
                style={{...styles.button, backgroundColor: '#ff4444'}} 
                onClick={() => deleteById('hive', h.id)}
              >
                Delete
              </button>
            </li>
          ))}
        </ul>

        <div style={styles.formGroup}>
          <h3>Add hive</h3>
          <div style={styles.formGroup}>
            <input 
              style={styles.input} 
              placeholder="Name of hive" 
              value={newHive.hiveName} 
              onChange={e => setNewHive({ ...newHive, hiveName: e.target.value })} 
            />
            <input 
              style={styles.input} 
              placeholder="Material" 
              value={newHive.hiveMaterial} 
              onChange={e => setNewHive({ ...newHive, hiveMaterial: e.target.value })} 
            />
            <input 
              style={styles.input} 
              placeholder="Type of hive" 
              value={newHive.hiveType} 
              onChange={e => setNewHive({ ...newHive, hiveType: e.target.value })} 
            />
            <input 
              style={styles.input} 
              placeholder="Frames per body" 
              value={newHive.framesPerBody} 
              onChange={e => setNewHive({ ...newHive, framesPerBody: e.target.value })} 
            />
          </div>
          <button 
            style={styles.button} 
            onClick={() => addItem('hive', newHive)}
          >
            ➕ Add hive
          </button>
        </div>

        <div style={styles.formGroup}>
          <h3>Find hive by ID</h3>
          <input 
            style={styles.input} 
            placeholder="Hive ID" 
            value={hiveId} 
            onChange={e => setHiveId(e.target.value)} 
          />
          <button 
            style={styles.button} 
            onClick={() => fetchById('hive', hiveId)}
          >
            🔍 Find
          </button>
          {hiveById && (
            <div style={{ marginTop: '10px', padding: '10px', backgroundColor: '#f0fff0', borderRadius: '5px' }}>
              Hive found: <strong>{hiveById.hiveName}</strong><br />
              Type: {hiveById.hiveType}<br />
              Material: {hiveById.hiveMaterial}<br />
              Frames: {hiveById.framesPerBody}
            </div>
          )}
        </div>
      </div>

      <div style={styles.section}>
        <h2>Bee families</h2>
        <button 
          style={styles.button} 
          onClick={() => fetchAll('beeFamily')}
        >
          🐝 Show all bee families
        </button>
        
        <ul style={styles.list}>
          {beeFamilies.map(b => (
            <li key={b.id} style={styles.listItem}>
              <div>
                <strong>{b.beeFamilyName}</strong> ({b.beeFamilyType})<br />
                Power of bee family: {b.beeFamilyPower}
              </div>
              <button 
                style={{...styles.button, backgroundColor: '#ff4444'}} 
                onClick={() => deleteById('beeFamily', b.id)}
              >
                Delete
              </button>
            </li>
          ))}
        </ul>

        <div style={styles.formGroup}>
          <h3>Add bee family</h3>
          <div style={styles.formGroup}>
            <input 
              style={styles.input} 
              placeholder="Name of bee family" 
              value={newBeeFamily.beeFamilyName} 
              onChange={e => setNewBeeFamily({ ...newBeeFamily, beeFamilyName: e.target.value })} 
            />
            <input 
              style={styles.input} 
              placeholder="Type of bee family" 
              value={newBeeFamily.beeFamilyType} 
              onChange={e => setNewBeeFamily({ ...newBeeFamily, beeFamilyType: e.target.value })} 
            />
            <input 
              style={styles.input} 
              placeholder="Power of bee family" 
              value={newBeeFamily.beeFamilyPower} 
              onChange={e => setNewBeeFamily({ ...newBeeFamily, beeFamilyPower: e.target.value })} 
            />
          </div>
          <button 
            style={styles.button} 
            onClick={() => addItem('beeFamily', newBeeFamily)}
          >
            ➕ Add bee family
          </button>
        </div>

        <div style={styles.formGroup}>
          <h3>Find bee family by ID</h3>
          <input 
            style={styles.input} 
            placeholder="Bee family ID" 
            value={beeFamilyId} 
            onChange={e => setBeeFamilyId(e.target.value)} 
          />
          <button 
            style={styles.button} 
            onClick={() => fetchById('beeFamily', beeFamilyId)}
          >
            🔍 Find
          </button>
          {beeFamilyById && (
            <div style={{ marginTop: '10px', padding: '10px', backgroundColor: '#f0fff0', borderRadius: '5px' }}>
              Bee family found: <strong>{beeFamilyById.beeFamilyName}</strong><br />
              Type: {beeFamilyById.beeFamilyType}<br />
              Power: {beeFamilyById.beeFamilyPower}
            </div>
          )}
        </div>
      </div>

      <AIChat />
    </div>
  );
}

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/app" element={<AppContent />} />
    </Routes>
  );
}

export default App;
