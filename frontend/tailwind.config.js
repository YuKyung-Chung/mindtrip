/** @type {import('tailwindcss').Config} */
const {nextui} = require("@nextui-org/react");

export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
    "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}"
  ],
  theme: {
    extend: {
      colors: {
        transparent: 'transparent',
        current: 'currentColor',
        apple : '#ef4444',
        orange : '#fea50b',
        pineapple : '#ffe35e',
        watermelon : '#a3e635',
        grape : '#5a74a0',
        peach : '#ffbdb2',
        blueberry : '#aacbf5'
      }
    },
  },
  darkMode: "class",
  plugins: [nextui()],
}
