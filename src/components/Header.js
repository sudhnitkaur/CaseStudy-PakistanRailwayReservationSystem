import React from 'react'
import { Link } from 'react-router-dom'

const Header = () => {
  return (
    <header className='header'>
      <div>
        <Link className='links' to='/'>
          HOME
        </Link>
      </div>

      <nav className='navbar'>
        <ul>
          <Link className='links' to='/login'>
            ADMIN
          </Link>

          

          <Link className='links' to='/search'>
            USER
          </Link>

          <Link className='links' to='/checkin'>
            Checkin
          </Link>

         
          
        </ul>
      </nav>
    </header>
  )
}

export default Header
