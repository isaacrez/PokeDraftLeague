import React from '../../node_modules/react';

function ArrowNavBar(props) {

    React.useEffect(() => {
        updatePage(0)
    }, [props.page.size]);

    function updatePage(shift) {
        props.page.setCurrent(page => {
            page += shift;

            page = page <= 0 ?
                    props.page.max + page % props.page.max : 
                props.page.max < page ? 
                    page % props.page.max : 
                    page;

            page = page > 0 ? page : 1;
            
            return page;
        })
    }

    return (
        <div>
            <button className="btn btn-secondary mx-1"
                onClick={() => (updatePage(-5))}>&#x00AB;</button>
            <button className="btn btn-secondary mx-1"
                onClick={() => (updatePage(-1))}>←</button>
            <button className="btn btn-secondary mx-1"
                onClick={() => (updatePage(1))}>→</button>
            <button className="btn btn-secondary mx-1"
                onClick={() => (updatePage(5))}>&#x00BB;</button>
        </div>
    );
}

export default ArrowNavBar;