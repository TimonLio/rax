--
-- PostgreSQL database dump
--

-- Started on 2009-05-04 20:23:17

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1497 (class 1259 OID 17466)
-- Dependencies: 6
-- Name: order_item; Type: TABLE; Schema: public; Owner: erp; Tablespace: 
--

CREATE TABLE order_item (
    id integer NOT NULL,
    order_id integer NOT NULL,
    ware_id integer NOT NULL,
    cost real NOT NULL,
    number integer NOT NULL
);


ALTER TABLE public.order_item OWNER TO erp;

--
-- TOC entry 1498 (class 1259 OID 17469)
-- Dependencies: 1497 6
-- Name: order_item_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE order_item_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.order_item_id_seq OWNER TO erp;

--
-- TOC entry 1782 (class 0 OID 0)
-- Dependencies: 1498
-- Name: order_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE order_item_id_seq OWNED BY order_item.id;


--
-- TOC entry 1776 (class 2604 OID 17510)
-- Dependencies: 1498 1497
-- Name: id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE order_item ALTER COLUMN id SET DEFAULT nextval('order_item_id_seq'::regclass);


--
-- TOC entry 1778 (class 2606 OID 17520)
-- Dependencies: 1497 1497
-- Name: order_lst_p_key; Type: CONSTRAINT; Schema: public; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY order_item
    ADD CONSTRAINT order_lst_p_key PRIMARY KEY (id);


--
-- TOC entry 1779 (class 1259 OID 17535)
-- Dependencies: 1497
-- Name: orderid_index; Type: INDEX; Schema: public; Owner: erp; Tablespace: 
--

CREATE INDEX orderid_index ON order_item USING btree (order_id);


-- Completed on 2009-05-04 20:23:18

--
-- PostgreSQL database dump complete
--

