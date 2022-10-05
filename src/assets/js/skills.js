customElements.define("svg-progress-circle", class extends HTMLElement {
    connectedCallback() {
      let d = 'M5,30a25,25,0,1,1,50,0a25,25,0,1,1,-50,0'; // circle
      this.innerHTML = 

      
      `<svg viewBox="0 0 60 60"	>
       <path    stroke-dashoffset="-19" 
             pathlength="120" d="${d}" fill="none" stroke="lightgrey" stroke-width="5"/>
       <path stroke-dasharray="30 70" stroke-dashoffset="-25" 
             pathlength="100" d="${d}" fill="none" 
             stroke="${this.getAttribute("color")||"red"}" stroke-width="5"/>
       <text font-size="10" x="50%" y="57%" text-anchor="middle">30%</text></svg>`;
       
      this.style.display='inline-block';
      this.percent = this.getAttribute("percent");
      this.habilidad = this.getAttribute("habilidad");
    }
    set percent(val = 0) {
      this.setAttribute("percent", val);
      let dash = val + " " + (100 - val);
      this.querySelector("path+path").setAttribute('stroke-dasharray', dash);
      
      
    }

    set habilidad (val = "") {
        this.setAttribute("habilidad", val);
        //let dash = val + " " + (100 - val);
        //this.querySelector("path+path").setAttribute('stroke-dasharray', dash);
        this.querySelector("text").innerHTML = val;

        
      }
  });

  //https://stackoverflow.com/questions/66990496/simple-svg-css-progress-circle