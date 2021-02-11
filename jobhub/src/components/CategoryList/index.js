import React, { useEffect } from 'react';
import * as ROUTES from '../../constants/routes';
import { httpClient } from '../../utils/HTTPBaseClient';
import Button from '@material-ui/core/Button';
import Modal from '@material-ui/core/Modal';
import ApplicantRequirementDetails from '../ApplicantRequiremtDetails';

import { getCurrentUser } from '../../redux/selectors/userSelectors';
import {connect, useDispatch, useSelector} from 'react-redux';

function CategoryList(props) {
    const { onClose = {} } = props

    const userObject = useSelector(getCurrentUser);

    useEffect(async() => {
        let response = await httpClient.getData(ROUTES.GET_CATEGORY_LIST);
        setCategoryList(response);
        global.setIsSelected();
    },[]);

    const [categoryList, setCategoryList] = React.useState({});
    const [openModalApplicantRequirementDetails, setOpenModalApplicantRequirementDetails] = React.useState(false);

    const handleOpenModalApplicantRequirementDetails = () => {
        setOpenModalApplicantRequirementDetails(true);
    };
    const handleCloseModalApplicantRequirementDetails = () => {
        setOpenModalApplicantRequirementDetails(false);
    };

    global.setIsSelected = () => {
        Object.values(categoryList).map(val => {
            console.log(val.name);
            val.isSelected=false;
            console.log(val);
        });
    }
    
  
    const handleToggle = async({ target }) => {
        Object.values(categoryList).map(val => {
            if(val.name == target.name){
                val.isSelected = !val.isSelected;
            }
        });
        await setCategoryList(categoryList);
        console.log("updated categoryList:",categoryList);
    }

    const onSubmit = async() => {
        let list=[];
        Object.values(categoryList).map(val => {
            if(val.isSelected){
                list=[...list, val];
            }
        });
        console.log(list);
        const response = await httpClient.postData(ROUTES.APPLY_CATEGORY_APPLICANT+userObject.id,list);
        alert("Categories Selected !")
        console.log(userObject);
        console.log(userObject.type === "Applicant");
        if(userObject.type === "Applicant"){
            setOpenModalApplicantRequirementDetails(true);
        }
    }


  
    return (
        <div>
            <div>
                {Object.values(categoryList).map(val => (
                    <div>
                        <input
                            type="checkbox"
                            onChange={handleToggle}
                            key={val.id}
                            name={val.name}
                            checked={val.isSelected}
                        />
                        <label>{val.name}</label>
                    </div>
                ))}
                <br />
                <Button size="small" variant="contained" color="primary" onClick={onSubmit} style={{ float: "left" }}>Submit</Button>
                <Button size="small" variant="contained" color="primary" onClick={onClose()} style={{ float: "right" }}>Close</Button>
            </div>

            <Modal
                open={openModalApplicantRequirementDetails}
                onClose={handleCloseModalApplicantRequirementDetails}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description" >
                    <div style={{columns: 2, backgroundColor: "white", margin:"10% 20% 10% 20%", padding:"2% 2% 2% 2%"}}>
                        {/* <CategoryList onClose={() => handleCloseModalApplicantRequirementDetails}/> */}
                        <h1>New Modal</h1>
                        <ApplicantRequirementDetails onClose={() => handleCloseModalApplicantRequirementDetails}/>
                    </div>
            </Modal>     
        </div>
    );
  }

export default CategoryList;