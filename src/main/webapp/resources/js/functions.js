function confimation(message){
	if(confirm(message)){
		return true;
	}else{
		return false;
	}
}
/*function confirmPassword(){
	const password = document.getElementById('password').value;
	const confirmPassword = document.getElementById('confirmPassword').value;
	const error = document.getElementById('error');
	if(password != confirmPassword){
		error.innerHTML = "Password doesn't match.";
		return false;
	}else{
		return true;
	}
}*/
document.querySelector('#form').addEventListener('submit',function(){
    	const password = document.getElementById('password').value;
    	const confirmPassword = document.getElementById('confirmPassword').value;
    	const error = document.getElementById('error');
    	if(password.length < 6){
			error.innerHTML = 'Password length must be at least 6 character.';
			event.preventDefault();
			return false;
		}else if(password !== confirmPassword){
    		error.innerHTML = "Password doesn't match.";
    		event.preventDefault();
    		return false;
    	}else{
    		return true;
    	}
    });
    function onlyNumber(input){
				input.value=input.value.replace(/[^0-9]/g, '');
			}