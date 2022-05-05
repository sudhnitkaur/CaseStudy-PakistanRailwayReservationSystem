import React from 'react'
import image1 from '../assets/image1.jpeg'
import image2 from '../assets/image2.gif'
import image3 from '../assets/image3.gif'

const Services = () => {
  return (
    <section className='services'>
      

      

     
   










        
      
      <div className='grid'>
        <div>
          <img src={image1} alt='service-1' />
          <h3>Hotels</h3>
          <p>
               Tell us your plans and we’ll find the best room for you:


          </p>
        </div>

        <div>
          <img src={image2} alt='service-2' />
          <h3>Holiday Packages</h3>
          <p>
          
        Explore, Journey, Discover, Adventure. Making your heart want to return. Don'T Be A Tourist, Be A Traveler. Every Day Is Different.
          </p>
        </div>

        <div>
          <img src={image3} alt='service-3' />
          <h3>E-Catering</h3>
          <p>
         Get restaurant food delivered right at your seat
          </p>
        </div>
      </div>
    </section>
  )
}

export default Services
