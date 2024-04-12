import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import { BrowserRouter } from 'react-router-dom'
import { NextUIProvider } from '@nextui-org/react'
import './index.css'
import { Provider } from 'react-redux'
import store, { persistor } from './store/store.ts'
import { PersistGate } from 'redux-persist/integration/react'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor} >
      <NextUIProvider>
        <BrowserRouter>
          <App />
        </BrowserRouter>
      </NextUIProvider>
    </PersistGate>
  </Provider>

)
