import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  resolve: {
    // Αυτό εμποδίζει το Vite από το να "μεταφράζει" το fitness-link 
    // στην πραγματική διαδρομή με τα κενά και το "&"
    preserveSymlinks: true
  }
})