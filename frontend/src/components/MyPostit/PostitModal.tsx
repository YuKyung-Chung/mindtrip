import { useState } from 'react';
import Modal, { Styles } from 'react-modal';

const customModalStyles: Styles = {
  overlay: {
    backgroundColor: "rgba(0, 0, 0, 0.4)",
    width: "100%",
    height: "100vh",
    zIndex: 10,
    position: "fixed",
    top: 0,
    left: 0,
  },
  content: {
    width: "360px",
    height: "180px",
    zIndex: 150,
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    borderRadius: "10px",
    boxShadow: "2px 2px 2px rgba(0, 0, 0, 0.25)",
    backgroundColor: "white",
    justifyContent: "center",
    overflow: "auto",
  },
};

Modal.setAppElement('#root');

const PostitModal = ({ isOpen, onClose, onSubmit }: { isOpen: boolean, onClose: () => void, onSubmit: (content: string) => void }) => {
  const [content, setContent] = useState('');

  const handleSubmit = () => {
    onSubmit(content);
    setContent('');
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onClose}
      style={customModalStyles}
      contentLabel="Postit Modal"
    >
      <button onClick={onClose} style={{ position: "absolute", top: "10px", right: "10px" }}>Close</button>
      <textarea value={content} onChange={(e) => setContent(e.target.value)} style={{ width: "100%", height: "100px", marginBottom: "10px" }} />
      <button onClick={handleSubmit}>Submit</button>
    </Modal>
  );
};

export default PostitModal;
