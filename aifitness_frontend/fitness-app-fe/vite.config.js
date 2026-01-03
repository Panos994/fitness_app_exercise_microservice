import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    fs: {
      // Επιτρέπει στο Vite να σερβίρει αρχεία έξω από το root αν χρειαστεί
      allow: ['..'] 
    }
  },
  // Αναγκάζουμε το Vite να χρησιμοποιεί σχετικές διαδρομές
  base: './'
})