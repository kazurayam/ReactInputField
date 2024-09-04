'use client';

// original: https://react.dev/learn/typescript#typing-dom-events

import { useState } from 'react';

export default function Form() {
  const [value, setValue] = useState('Change me');

  function handleChange(event: React.ChangeEvent<HTMLInputElement>) {
    setValue(event.currentTarget.value);
  }

  return (
    <>
      <input value={value} onChange={handleChange}/>
      <p>Value: {value}</p>
    </>
  );
}
